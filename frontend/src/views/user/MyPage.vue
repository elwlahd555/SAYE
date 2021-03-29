<template>
  <v-container color="black">
    <v-row>
      <h1>나만의 플레이리스트</h1>
    </v-row>
    <v-row>
      <div id="root" class="playlist">
        <ul class="items">
          <drag title="Time / Date"></drag>
          <drag title="Location"></drag>
        </ul>

        <dropzone :class="{ 'dropzone--over': isOver }"></dropzone>
      </div>
    </v-row>
  </v-container>
</template>

<script>
import Dropzone from "@/components/mypage/Dropzone";
import Drag from "@/components/mypage/Drag";

export default {
  components: {
    Dropzone,
    Drag
  },
  data() {
    return {
      isOver: false
    };
  },
  created() {
    this.$on("hitDropZone", function(value) {
      this.isOver = value;
    });
  }
};
</script>

<style lang="scss" scoped>
.playlist {
  margin-top: 50px;
  padding: 0 5%;
  display: flex;
}

.items {
  padding: 10px;
  margin-right: 20px;
  border: 4px solid black;
  width: 320px;
  height: 400px;
}

.item {
  position: relative;
  margin-bottom: 10px;
  height: 50px;

  &__drag {
    position: absolute;
    left: 0;
    right: 0;
    padding-top: 15px;
    height: 50px;
    background-color: #6495ed;
    text-align: center;
    font-weight: 700;
    line-height: 1;
    transition: background-color, border 0.2s ease-in;

    &:active {
      background-color: #3b6cc3;
      border: 1px solid black;
    }
  }
}

.dropzone {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  border: 4px dotted black;
  background-color: lightgrey;
  transition: background-color 0.2s linear;

  &__txt {
    font-weight: 700;
    font-size: 30px;
    color: black;
  }

  &--over {
    background-color: #dc143c;
    color: white;
  }

  &--over &__icon {
    fill: white;
  }

  &--over &__txt {
    color: white;
  }
}
</style>
