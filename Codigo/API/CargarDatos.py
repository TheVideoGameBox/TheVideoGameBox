from igdb.wrapper import IGDBWrapper
wrapper = IGDBWrapper("18tfq728xi2b0cxvsnhs69znuzrelk", "ohuyxp4ws2kyof9mzq9i24odno25u0")

import requests
import json
import pandas as pd

url = 'https://id.twitch.tv/oauth2/token'
myobj = {'client_id': '18tfq728xi2b0cxvsnhs69znuzrelk',
          'client_secret': 'ohuyxp4ws2kyof9mzq9i24odno25u0',
          'grant_type':'client_credentials'}

x = requests.post(url, data = myobj)

print(x.text)
i = x.json()

wrapper = IGDBWrapper("18tfq728xi2b0cxvsnhs69znuzrelk", i['access_token'])
# Obtencion de los juegos

print("Obtencion de los juegos")

num = [0,500,1000,1500,2000,2500]

for n in num:
    
    from igdb.igdbapi_pb2 import GameResult
    byte_array = wrapper.api_request(
                'games', 
                f'f name, cover.url, involved_companies.company.name, genres.name, platforms.name, summary, release_dates.date; l 500; offset {n};'
              )
    games_message = GameResult()
    
    # Funcion para decodificar los bytes obtenidos en GameResult(), cargarlo como json y pasarlo a un DataFrame
    
    data = pd.json_normalize(json.loads(byte_array.decode('utf-8')))
    
    data = data.set_index('id')
    
    data = data.rename(columns={'cover.url' : 'cover', 'cover.id' : 'image'})
    
    # Obtencion de los generos
    
    print("Obtencion de los generos")
    
    genres = data["genres"]
    gen = []
    for g in genres:
       if str(g) != 'nan':
            aux = []
            for ge in g:
                aux.append(ge["name"])
            gen.append(aux)
       else:
           gen.append(g)
    data["genres"] = gen
    
    # Obtencion de las plataformas
    
    print("Obtencion de las plataformas")
    
    platforms = data["platforms"]
    plat = []
    for pla in platforms:
       if str(pla) != 'nan':
            aux = []
            for p in pla:
                aux.append(p["name"])
            plat.append(aux)
       else:
           plat.append(pla)
    data["platforms"] = plat
    
    # Obtencion de las compañias
    
    print("Obtencion de las compañias")
    
    companies = data["involved_companies"]
    compa = []
    for com in companies:
       if str(com) != 'nan':
            aux = []
            for c in com:
                aux.append(c["company"]["name"])
            compa.append(aux)
       else:
           compa.append(com)
    data["involved_companies"] = compa
    
    # Obtencion de las portadas
    
    print("Obtencion de las portadas")
    
    covers = data["cover"]
    cov = []
    for co in covers:
        if str(co) != 'nan':
            a = co.replace('t_thumb', 't_cover_big')
            cov.append(a)
        else:
            cov.append(co)
    data["image"] = cov
    
    # Obtencion de las fechas
    
    print("Obtencion de las fechas")
    
    dates = data["release_dates"]
    dat = []
    for da in dates:
       if str(da) != 'nan':
           if len(da[0]) > 1:
                dat.append(da[0]["date"])
           else: dat.append(-1)
       else:
           dat.append(-1)
    data["release_dates"] = dat
    if n == 0:
        data1 = data
    elif n == 500:
        data2 = data
    elif n == 1000:
        data3 = data
    elif n == 1500:
        data4 = data
    elif n == 2000:
        data5 = data
    else: data6 = data

dataBase = pd.concat([data1, data2, data3, data4, data5, data6])

dataBase.to_json("games1.json", orient='index')