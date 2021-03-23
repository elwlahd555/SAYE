<template>
  <v-container fluid>
    <div class="searchBox">
      <input class="searchInput" type="text" name="" placeholder="Search" />
      <v-btn class="searchButton" color="#d1ebd9" depressed fab x-large>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  components: {}
};
</script>

<style scoped>
.searchBox {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, 50%);
  background: #2f3640;
  height: 90px;
  border-radius: 40px;
  padding: 10px;
}

.searchBox:hover > .searchInput {
  width: 440px;
  padding: 0 6px;
}

.searchBox:hover > .searchBtton {
  background: white;
  color: #d1ebd9;
}

.searchButton {
  float: right;
  background: #d1ebd9;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.4s;
}

.searchInput {
  border: none;
  background: none;
  outline: none;
  float: left;
  color: white;
  font-size: 1.5em;
  transition: 0.4s;
  line-height: 70px;
  width: 0px;
  transform: translateX(20px);
}

@media screen and (max-width: 1250px) {
  .searchBox:hover > .searchInput {
    width: 300px;
    padding: 0 6px;
  }
}
</style>
