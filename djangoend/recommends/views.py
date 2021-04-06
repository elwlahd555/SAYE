import pandas as pd
import sklearn

from django.shortcuts import get_object_or_404
from django.db import connection
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from sklearn.metrics.pairwise import cosine_similarity

from .models import Rcd
from .serializers import RcdListSerializer


# class ViewParams(View):
#     def get(self, request):
#         emotion = request.GET.get('emotion', None)
#         music_id = request.GET.get('music_id', None)
#         num = request.GET.get('requestCnt', 4)
#         return emotion, music_id, num

# Create your views here.
@api_view(['GET'])
def recommend_music(request):
    emotion= request.GET['emotion']
    music_id = request.GET['music_id']
    num = request.GET['requestCnt']
    print(emotion+" "+music_id+" "+num)

    df, labels = getByPandas(emotion)
    df = scale_data(df,labels)

    # emotion, music_id, requestCnt
    df = df[music_id].sort_values(ascending=False).iloc[1:1+int(num)]
    rcds = Rcd.objects.filter(m_id__in=df.index.to_list())
    for rcd in rcds:
        print(rcd)
    serializer = RcdListSerializer(rcds, many=True)
    print(serializer.data)
    return Response(serializer.data)


def getByPandas(emotion):
    query="select distinct atd.* from audio_transition_data atd inner join music m on m.m_id = atd.id where m.m_emotion = {e};".format(e="'"+emotion+"'")
    df = pd.read_sql(query, connection)
    df = df.drop(columns=['a_no','artist','title','genre','url']).set_index(['id'])
    labels = list(df.index)
    return df, labels

def scale_data(df, labels):
    df_scaled = sklearn.preprocessing.scale(df)
    df_scaled = pd.DataFrame(df_scaled,columns=df.columns)

    similarity = cosine_similarity(df_scaled)
    df_sim = pd.DataFrame(similarity, index=labels, columns=labels)
    return df_sim