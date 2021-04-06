from django.db import models

# Create your models here.
class Rcd(models.Model):
    m_no = models.IntegerField(auto_created=True, primary_key=True)
    m_title = models.TextField()
    m_genre = models.TextField()
    m_artist = models.TextField()
    m_album = models.TextField()
    m_preview = models.TextField()
    m_img = models.TextField()
    m_popularity = models.TextField()
    m_id = models.CharField(max_length=200)
    m_artist_id = models.TextField()
    m_album_id = models.TextField()
    m_cnt = models.IntegerField()
    m_emotion = models.TextField()
    m_date = models.TextField()
    m_url = models.TextField()

    class Meta:
        managed= False
        db_table= "music"

 