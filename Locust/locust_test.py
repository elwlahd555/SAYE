# 일단 코드별로 주석은 달았으나, 정확한 의미를 모른다면 밑의 개념설명을 읽고 올것을 권장함
from locust import HttpUser, TaskSet, task, between
import json


class MyTaskSet(HttpUser):
    host = "https://j4d106.p.ssafy.io"

    @task(7)
    def index(self):
        self.client.get("/login")

    # '3'의 비율로 음악 검색(post) 작업 테스팅(POST)
    @task(3)
    def signupTask(self):
        self.client.get("/signup")

    @task(4)
    def detailBoardTask(self):
        self.client.get("/detail/11")

    @task(1)
    def searchMusicTask(self):
        self.client.get("/spotify/search")

    @task(3)
    def listPlaylistTask(self):
        self.client.get("/playlist/belong")

    @task(1)
    def reviewPlaylist(self):
        self.client.get("/playlist")

    @task(4)
    def reviewLikeMusic(self):
        self.client.get("/playlist/likemusic")

    @task(3)
    def reviewComment(self):
        self.client.get("/board/comment")


# locust -f locust_test.py --host=https://j4d106.p.ssafy.io
