<template>
  <article
    id="panel-3"
    class="panel full-screen pink"
    style="background: ffb6c1"
  >
    <v-container fill-height>
      <v-row justify="center">
        <v-col>
          <div class="writeBox">
            <v-btn
              class="writeButton"
              depressed
              fab
              x-large
              :to="{ name: 'BoardDetailPage' }"
            >
              <v-icon>mdi-lead-pencil</v-icon>
            </v-btn>
          </div>
        </v-col>
        <v-col>
          <div class="listBox">
            <v-btn
              class="listButton"
              depressed
              fab
              x-large
              :to="{ name: 'BoardPage' }"
            >
              <v-icon>mdi-view-list</v-icon>
            </v-btn>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </article>
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.writeBox {
  position: absolute;
  top: 40%;
  left: 28%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.writeButton {
  float: right;
  background: #ffdca7;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}

.listBox {
  position: absolute;
  top: 40%;
  left: 72%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.listButton {
  float: right;
  background: #ffdca7;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}
</style>
