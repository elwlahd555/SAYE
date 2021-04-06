# 해당 작업은 순차적인 서비스 테스팅을 원할 때 하는 스크립트
# 단, 이 작업은 현재 동작되지않음(내가못한다기보다는 이거 사용이 잘안된다고함)

from locust import HttpLocust, TaskSet, task, between


class MyTaskSequence(TaskSequence):
    @seq_task(1)  # 첫번째 작업
    @task(2)  # 2번 수행하겠다(로그인)
    def login_task(self):
        self.client.get("/login")

    @seq_task(2)  # 두번째 작업
    @task(3)  # 3번 수행하겠다(게시판 작성)
    def board_write_task(self):
        self.client.get("/SAYE")

    @seq_task(3)  # 세번재 작업
    @task(10)  # 10번 수행하겠다(게시판조회)
    def board_list_task(self):
        self.client.get("/SAYE")
