from json import load
from pymongo import MongoClient

client = MongoClient('mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority')
db = client['TVGB']
games = db.get_collection('games')

with open('games3.json') as file:
    file_data = load(file)

    for data in file_data:
        values = file_data.get(data)

        if len(values["name"]) <= 25 and "hentai" not in str(values["name"]).lower() and values["image"] and values["cover"]:
            games.insert_one(values)
