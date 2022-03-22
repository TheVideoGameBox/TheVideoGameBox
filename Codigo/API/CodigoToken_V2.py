from igdb.wrapper import IGDBWrapper

wrapper = IGDBWrapper("18tfq728xi2b0cxvsnhs69znuzrelk", "ohuyxp4ws2kyof9mzq9i24odno25u0")

import requests
import json
import pandas as pd

url = 'https://id.twitch.tv/oauth2/token'
myobj = {'client_id': '18tfq728xi2b0cxvsnhs69znuzrelk',
         'client_secret': 'ohuyxp4ws2kyof9mzq9i24odno25u0',
         'grant_type': 'client_credentials'}

x = requests.post(url, data=myobj)

print(x.text)
i = x.json()

wrapper = IGDBWrapper("18tfq728xi2b0cxvsnhs69znuzrelk", i['access_token'])


# Funcion para obtener las plataformas con sus respectivos juegos

def getPlatforms(platforms, games):
    result = []
    for g in games:
        aux = []
        if str(g) != 'nan':
            for p in g:
                aux.append(platforms[p])
        result.append(aux)
    return result


# Funcion para obtener las portadas con sus respectivos juegos

def getCovers(covers):
    result = []
    for c in covers:
        from igdb.igdbapi_pb2 import CoverResult
        byte_array3 = wrapper.api_request(
            'covers',
            f'f url; l 1; w id = {c};'
        )
        cover_message = CoverResult()
        cov = pd.json_normalize(json.loads(byte_array3.decode('utf-8')))
        if cov.empty:
            result.append('nan')
        else:
            data2 = cov["url"][0]
            result.append(data2)
    return result


# Funcion para obtener los generos con sus respectivos juegos

def getGenres(genres, games):
    result = []
    for g in games:
        aux = []
        if str(g) != 'nan':
            for n in g:
                aux.append(genres[n])
        result.append(aux)
    return result


# Funcion para obtener los companias con sus respectivos juegos

def getInvolvedCompanies(companies):
    result = []
    for c in companies:
        if str(c) != 'nan':
            aux = []
            for cm in c:
                from igdb.igdbapi_pb2 import InvolvedCompanyResult
                byte_array4 = wrapper.api_request(
                    'involved_companies',
                    f'f company; l 1; w id ={cm};'
                )
                involved_companies = InvolvedCompanyResult()
                com = pd.json_normalize(json.loads(byte_array4.decode('utf-8')))
                data2 = com["company"].to_list()
                aux.append(data2)
            result.append(aux)
        else:
            result.append('nan')
    return result


def getCompanies(company):
    result = []
    for c in company:
        if str(c) != 'nan':
            aux = []
            for cm in c:
                from igdb.igdbapi_pb2 import CompanyResult
                byte_array5 = wrapper.api_request(
                    'companies',
                    f'f name; l 1; w id ={cm[0]};'
                )
                comp = CompanyResult()
                com = pd.json_normalize(json.loads(byte_array5.decode('utf-8')))
                data3 = com["name"].to_list()
                aux.append(data3)
            result.append(aux)
        else:
            result.append('nan')
    return result


# Funcion para obtener las fechas con sus respectivos juegos

def getDates(dates):
    result = []
    for d in dates:
        if str(d) != 'nan':
            da = d[0]
            from igdb.igdbapi_pb2 import ReleaseDateResult
            byte_array6 = wrapper.api_request(
                'release_dates',
                f'f date; l 1; w id = {da};'
            )
            dates_message = ReleaseDateResult()
            cov = pd.json_normalize(json.loads(byte_array6.decode('utf-8')))
            if len(cov.columns) > 1:
                data6 = cov["date"][0]
                result.append(data6)
            else:
                result.append('nan')
        else:
            result.append('nan')
    return result


# Obtencion de los juegos

print("Obtencion de los juegos")

from igdb.igdbapi_pb2 import GameResult

byte_array = wrapper.api_request(
    'games',
    'f name, cover, involved_companies, genres, platforms, summary, release_dates; l 500; offset 0;'
)
games_message = GameResult()

# Funcion para decodificar los bytes obtenidos en GameResult(), cargarlo como json y pasarlo a un DataFrame

data = pd.json_normalize(json.loads(byte_array.decode('utf-8')))

# Obtencion de las portadas

print("Obtencion de las portadas")

covers = data["id"].to_list();

r2 = getCovers(covers)

# Lo indexo segun el id

data = data.set_index('id')

data["cover"] = r2

# Obtencion de las plataformas

print("Obtencion de las plataformas")

from igdb.igdbapi_pb2 import PlatformResult

byte_array2 = wrapper.api_request(
    'platforms',
    'f name; l 500;'
)

platform_message = PlatformResult()

# Funcion para decodificar los bytes obtenidos en PlatformResult(), cargarlo como json y pasarlo a un DataFrame

data2 = pd.json_normalize(json.loads(byte_array2.decode('utf-8')))

platforms = data2.set_index('id').to_dict()['name']

games = data["platforms"].to_list();

r = getPlatforms(platforms, games)

data["platforms"] = r

# Obtencion de las categorias

print("Obtencion de las categorias")

from igdb.igdbapi_pb2 import GenreResult

byte_array3 = wrapper.api_request(
    'genres',
    f'f name; l 500;'
)

genre_message = GenreResult()

# Funcion para decodificar los bytes obtenidos en PlatformResult(), cargarlo como json y pasarlo a un DataFrame

data3 = pd.json_normalize(json.loads(byte_array3.decode('utf-8')))

genres = data3.set_index('id').to_dict()['name']

games = data["genres"].to_list();

r3 = getGenres(genres, games)

data["genres"] = r3

# Obtencion de las companias

print("Obtencion de las companias")

involved_companies = data["involved_companies"].to_list();

r4 = getInvolvedCompanies(involved_companies)

r5 = getCompanies(r4)

data["involved_companies"] = r5

# Obtencion de las fechas

print("Obtencion de las fechas")

dates = data["release_dates"].to_list();

r6 = getDates(dates)

data["release_dates"] = r6

# Guardar a la Ãºltima version (remplazar los \/ con /)
data.to_json("games.json", orient='index')