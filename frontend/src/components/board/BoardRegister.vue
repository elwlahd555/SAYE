<template>
  <div align="center">
    <table align="center" id="tableBoardRegister">
      <tr class="lineBoard">
        <thead align="center">
          분류
        </thead>
        <td>
          <select name="bClass" id="bClass" v-model="form.bClass">
            <option value="하소연" selected>하소연</option>
            <option value="도움글">도움글</option>
          </select>
        </td>
      </tr>
      <tr class="lineBoard">
        <thead
          align="center"
          label-for="bTitle"
          description="게시글의 작성자익명을 입력하세요."
        >
          작성자명
        </thead>
        <td>
          <input
            id="bTitle"
            v-model="form.bTitle"
            type="text"
            required
            placeholder="제목 입력..."
          />
        </td>
      </tr>
      <tr class="lineBoard">
        <thead algin="center">
          내용
        </thead>
        <td>
          <textarea
            id="bContent"
            v-model="form.bContent"
            placeholder="내용 입력..."
            rows="15"
            max-rows="20"
          ></textarea>
        </td>
      </tr>
    </table>
    <div align="center">
      <v-btn type="submit" outlined color="blue" small class="writeBtn"
        >글작성</v-btn
      >
      <v-btn type="reset" outlined color="error" small class="cancel"
        >취소</v-btn
      >
    </div>
  </div>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;
export default {
  name: "BoardRegister",

  data() {
    return {
      board: {
        bNo: 0,
        bClass: "",
        bWriter: "",
        bContent: "",
        bDate: "",
        bUNo: 0,
        bViewCnt: 0,
      },
      form: {
        bClass: "",
        bWriter: "",
        bContent: "",
      },
    };
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();
      if (this.$refs.form.validate()) {
        axios
          .post(`${spring_URL}/board/add`, this.board)
          .then(() => {
            this.$router.push({ name: "BoardList" });
          })
          .catch((error) => {
            console.log(error);
          });
      } else {
        console.log("정보 덜채워짐");
      }
    },
    onReset(event) {
      event.preventDefault();
      this.form.bTitle = "";
      this.form.bContent = "";
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap");

.lineBoard {
  background-color: #f7c9cb;
  font-family: "Do Hyeon", sans-serif;
}
.lineBoard thead {
  padding-top: 5px;
  padding-bottom: 5px;
  font-family: "Do Hyeon", sans-serif;
  font-size: 17px;
}
.lineBoard td {
  font-family: "Do Hyeon", sans-serif;
  font-size: 17px;
  width: 300px;
}

#bClass {
  width: 300px;
  padding: 5px;
  appearance: none;
}
</style>