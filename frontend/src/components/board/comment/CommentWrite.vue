<template>
  <v-form>
    <v-container>
      <v-row>
        <v-col cols="12">
          <v-text-field
            v-model="form.cContent"
            :append-outer-icon="form.cContent ? 'mdi-send' : 'mdi-alert-circle'"
            :prepend-icon="icon"
            filled
            clear-icon="mdi-close-circle"
            clearable
            label="Message 입력 후 오른쪽 아이콘을 클릭해주세요"
            @click:append-outer="commentWrite"
            @click:prepend="changeIcon"
            @click:clear="clearMessage"
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-form>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const spring_URL = process.env.VUE_APP_SPRING_URL;

var rand = Math.random().toString(36).substr(2, 11);

export default {
  name: "commentwrite",

  data() {
    return {
      form: {
        cBNo: this.$route.params.bNo,
        cContent: "",
        cUNo: this.$store.state.uId,
        cWriter: rand,
      },
      marker: true,
      iconIndex: 0,
      icons: [
        "mdi-emoticon",
        "mdi-emoticon-cool",
        "mdi-emoticon-dead",
        "mdi-emoticon-excited",
        "mdi-emoticon-happy",
        "mdi-emoticon-neutral",
        "mdi-emoticon-sad",
        "mdi-emoticon-tongue",
      ],
    };
  },

  computed: {
    ...mapState({
      uNo: "uId",
    }),
    icon() {
      return this.icons[this.iconIndex];
    },
  },
  methods: {
    commentWrite: function () {
      console.log(this.form);
      axios
        .post(`${spring_URL}/board/comment/add`, this.form)
        .then((response) => {
          console.log(response.data);
          this.$emit("commentRefresh");
          this.resetIcon();
          this.clearMessage();
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    enterWrite() {
      console.log(this.form);
    },
    clearMessage() {
      this.message = "";
    },
    resetIcon() {
      this.iconIndex = 0;
    },
    changeIcon() {
      this.iconIndex === this.icons.length - 1
        ? (this.iconIndex = 0)
        : this.iconIndex++;
    },
  },
};
</script>

<style></style>
