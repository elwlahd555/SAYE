from django.urls import path
from . import views

urlpatterns = [
    path('<str:emotion>/', views.recommend_music),
]