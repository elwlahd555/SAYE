# FROM python:3.6
# ENV PYTHONUNBUFFERED=1
# RUN pip install -r requirements.txt
# WORKDIR /usr/src/app
# COPY . .
# WORKDIR ./djangoend
# CMD ["python", "manage.py", "runserver", "0:8888"]
# EXPOSE 8888

FROM python:3
ENV PYTHONUNBUFFERED=1
COPY . .
RUN pip install -r requirements.txt
CMD ["python", "manage.py", "runserver", "0:8888"]
EXPOSE 8888

