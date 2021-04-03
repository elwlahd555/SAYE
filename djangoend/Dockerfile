FROM python:3.6
RUN pip3 install django
WORKDIR /usr/src/app
COPY . .
WORKDIR ./djangoend
CMD ["python3", "manage.py", "runserver", "0:8888"]
EXPOSE 8888
