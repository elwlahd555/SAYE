# Nesting 작업을 위한 파이썬스크립트
# UserBehavior TaskSet에서 Nested라는 TaskSet 호출 예제
# UserBehavior에는 index라는 Task와 Nested TaskSet이 정의됨
# Nested TaskSet은 가중치가 2로 되어 있기 때문에, index에 비해 2배 많이 호출됨
# Nested TaskSet은 3개의 task를 가지고 있는데, 가중치가 1로써
# 이 셋중 하나가 랜덤으로 실행
# Nested Task 안의 task만 계속 실행됨
# (즉, 상위 TaskSet UserBehavior가 원칙적으로는 다시 호출X)
# NestedTaskSet의 task들로 루프를 돈다는 말!
# stop task에 self.interrupt를 호출해서 상위 TaskSet되도록함

from locust import Locust, TaskSet, task, between


class Nested(TaskSet):
    @task(1)
    def task1(self):
        print("task1")

    @task(1)
    def task2(self):
        print('task2')

    @task(1)
    def task3(self):
        print("stop")
        self.interrupt()


class UserBehavior(TaskSet):
    tasks = {Nested: 2}

    @task
    def index(self):
        print("user behavior task")
