FROM python:3.6
RUN pip3 install django
WORKDIR /usr/src/app
COPY . .
WORKDIR ./djangoend
CMD ["python", "manage.py", "runserver", "0:8888"]
EXPOSE 8888

FROM python:3
ENV PYTHONUNBUFFERED=1
COPY . .
WORKDIR ./djangoend
COPY requirements.txt /code/
RUN pip install -r requirements.txt
CMD ["python", "manage.py", "runserver", "0:8888"]
EXPOSE 8888

