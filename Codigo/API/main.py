from json import load
from pymongo import MongoClient

if __name__ == '__main__':
    client = MongoClient('mongodb+srv://MongoDB-GPS:ZUCUvvYbKW@gps.iox2a.mongodb.net/myFirstDatabase?retryWrites=true&w=majority')
    db = client['TVGB']
    games = db.get_collection('games')

    with open('games.json') as file:
        file_data = load(file)

        for data in file_data:
            games.insert_one(file_data.get(data))
