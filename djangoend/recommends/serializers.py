from rest_framework import serializers
from .models import RCD

class RCDListSerializer(serializers.ModelSerializer):
    class Meta:
        model = RCD