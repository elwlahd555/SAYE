<template>
  <v-container class="bv-example-row mt-3">
    <v-row>
      <v-col><h3>글수정</h3></v-col>
    </v-row>
    <v-row class="mv-1">
      <v-col style="text-align: left">
        <v-form @submit="onSubmit" @reset="onReset">
          <v-form-group
            id="bTitle-group"
            label="제목:"
            label-for="bTitle"
            description="제목을 입력하세요."
          >
            <v-form-input
              id="bTitle"
              v-model="form.bTitle"
              type="text"
              required
              placeholder="제목 입력..."
            ></v-form-input>
          </v-form-group>

          <v-form-group id="content-group" label="내용:" label-for="bContent">
            <v-form-textarea
              id="bContent"
              v-model="form.bContent"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></v-form-textarea>
          </v-form-group>

          <v-button type="submit" variant="primary" class="m-1"
            >글작성</v-button
          >
          <v-button type="reset" variant="danger" class="m-1">초기화</v-button>
        </v-form>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;
const config = {
  headers: { "auth-token": window.localStorage.getItem("auth-token") },
};

export default {
  name: "BoardEdit",

  data() {
    return {
      form: {
        bNo: 0,
        bClass: "",
        bTitle: "",
        bContent: "",
        bWriter: "",
        bDate: "",
        bUNo: 0,
        bViewCnt: 0,
      },
    };
  },

  mounted() {
    this.getBoard();
  },

  methods: {
    getBoard: function () {
      axios
        .get(`${spring_URL}/board?bNo=${this.bNo}`)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // editBoard() {
    //   axios
    //     .put(`${spring_URL}/board/edit?`, this.form, config)
    //     .then((response) => {
    //       console.log(response.data);
    //       this.$router.push({ name: "board-list" });
    //     })
    //     .catch((error) => {
    //       console.log(error);
    //     });
    // },

    onSubmit(event) {
      event.preventDefault();

      axios
        .put(`${spring_URL}/board/edit?bNo=${this.bNo}`, this.form, config)
        .then((response) => {
          console.log(response.data);
          this.$router.push({ name: "board-list" });
        })
        .catch((error) => {
          console.log(error);
        });
    },

    onReset(event) {
      event.preventDefault();
      // this.form.cTitle="";
      // this.form.cContent="";
      this.$router.push({ name: "board-list" });
    },
  },
};
</script>

<style>
</style>