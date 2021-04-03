from django.shortcuts import get_objects_or_404
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view

# from .models import Recommend
# from .serializers import RecommendSerializer

# Create your views here.
@api_view(['GET'])
def recommend_music(request):
    pass
