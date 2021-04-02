<template>
  <div>
    <div id="btnGroup" align="center">
      <button id="helpBtn">도움글</button>
      <button id="sayBtn">하소연</button>
    </div>
    <board-list />
  </div>
</template>

<script>
import BoardList from '@/components/board/BoardList.vue';

export default {
  components: {
    BoardList,
  },
  name: 'BoardPage',
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');

#helpBtn {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  margin-right: -1px;
}
#sayBtn {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
#btnGroup button {
  width: 43px;
  margin-top: 150px;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: 'Nanum Pen Script', cursive;
}
#btnGroup button:hover {
  color: white;
  background-color: #f7c9cb;
}
</style>
