import pandas as pd
import sklearn

from django.shortcuts import get_object_or_404
from django.db import connection
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from sklearn.metrics.pairwise import cosine_similarity

from .models import RCD
# from .serializers import RecommendSerializer


# Create your views here.
@api_view(['GET'])
def recommend_music(request,emotion):
    df, labels = getByPandas()
    df = scale_data(df,labels)
    df = df['63xdwScd1Ai1GigAwQxE8y'].sort_values(ascending=False).iloc[1:]
    print(df.head(10))
    
    arr = []
    rcds = RCD.objects.filter(m_emotion=emotion)
    return Response('')


def getByPandas():
    query="SELECT * FROM audio_transition_data"
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