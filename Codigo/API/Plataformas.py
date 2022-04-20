from json import load, dump

jsons = ["games1.json", "games2.json", "games3.json"]

result = set()
for json in jsons:  #Recorrer todos los jsons
    with open(json) as file:
        file_data = load(file)

        for game in file_data.values():  #Recorrer todos los juegos del json
            platforms = game['platforms']

            if platforms:
                for platform in platforms:  #Recorrer todas las plataformas del juego
                    if platform:
                        result.add(platform)


with open('platforms.json', 'w') as f:
    dump(sorted(result), f, ensure_ascii=False, indent=4)