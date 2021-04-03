FROM python:3
ENV PYTHONUNBUFFERED=1
COPY . .
WORKDIR ./djangoend
RUN pip install -r requirements.txt
CMD ["python", "manage.py", "runserver", "0:8888"]
EXPOSE 8888

