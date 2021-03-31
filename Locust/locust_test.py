from locust import HttpUser, TaskSet, task, between


class MyTaskSet(TaskSet):

    @task
    def index(self):
        self.client.get("/SAYE")


class MyLocus(HttpUser):
    task_set = MyTaskSet
    wait_time = between(3, 5)
