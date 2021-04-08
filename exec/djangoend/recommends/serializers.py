from rest_framework import serializers
from .models import Rcd

class RcdListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Rcd
        fields='__all__'