from spotipy.oauth2 import SpotifyClientCredentials
import spotipy
import pprint
import json

client_id="f244c17b7e864e84b355f04cdedb22ff"
client_secret = "be1729d17f274a068dd9b70ee7c982fb"
genres = ['blues','classical','country','disco','hip hop','jazz','metal','pop','reggae','rock']

client_credentials_manager = SpotifyClientCredentials(client_id=client_id, client_secret=client_secret)
sp = spotipy.Spotify(client_credentials_manager = client_credentials_manager)

def search(n=3):
    res={}
    for genre in genres:
        urls=[]
        for i in range(0,n):
            results = sp.search(q='genre:' + genre, type='track',offset=i,limit=50)
            tracks = results['tracks']['items']
            for track in tracks:
                if track['preview_url'] is not None:
                    urls.append(track['preview_url']+'.wav')
                else:
                    pass
            res[genre] = urls
    return res

res = search()
for key,value in res.items():
    print('genre : '+key)
    print(len(value))


def search_by_genre(genre):
    results = sp.search(q='genre:' + genre, type='track',limit=100)
    tracks = results['tracks']['items']
    urls=[]
    for track in tracks:
        if track['preview_url'] is not None:
            urls.append(track['preview_url'])
        else:
            pass
            

    return len(urls)

# print(search_by_genre('hiphop'))
