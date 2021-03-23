<template>
  <header class="site-header" role="banner">
    <div class="wrapper">
      <nav class="anchor-nav menu ml-10" role="navigation">
        <a href="#" class="one">Home</a>
        <a href="#intro" class="anchor">인기 차트</a>
        <a href="#panel-1" class="anchor two">Music Search</a>
        <a href="#panel-2" class="anchor three">Emotion Analysis</a>
        <a href="#panel-3" class="anchor four">Board</a>

        <transition name="component-fade" mode="out-in">
          <div class="circle" v-show="scY > 900">
            <ul>
              <li>1</li>
              <li>2</li>
              <li>3</li>
              <li>4</li>
            </ul>
          </div>
        </transition>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      scY: 0
    };
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll: function() {
      this.scY = window.scrollY;
      //console.log(this.scY);
    }
  }
};
</script>

<style scoped>
.component-fade-enter-active,
.component-fade-leave-active {
  transition: opacity 0.3s ease;
}
.component-fade-enter, .component-fade-leave-to
/* .component-fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}

/*WRAPPER*/
.wrapper {
  position: fixed;
  margin: 20px auto;
  z-index: 999;
}

.menu a {
  margin-right: -4px;
  padding: 10px 30px;
  width: 50px;
  color: whitesmoke;
  text-decoration: none;
  font-weight: bold;
}

.menu a:hover {
  background: grey;
}

/*INNER CIRCLE*/
.wrapper:before {
  content: "SAYE";
  text-align: center;
  font: 35px/95px Georgia, Times, serif;
  color: black;
  position: absolute;
  top: 80vh;
  left: 45vw;
  z-index: 10;
  width: 10vw;
  height: 10vw;
  border-radius: 50%;
  background: #fff;

  box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.3);
}

/*MAIN CIRCLE*/
.circle {
  position: absolute;
  width: 40vw;
  height: 40vw;
  border-radius: 50%;
  background: black;
  top: 63vh;
  left: 30vw;
  box-shadow: inset 0px 0px 30px rgba(0, 0, 0, 0.3);
  transition: all 0.5s ease;
}

/*LITTLE CIRCLES*/
.circle li {
  position: absolute;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: white;
  list-style-type: none;
  text-align: center;
  font: 20px/50px Helvetica, Arial, sans-serif;
  top: 0;
  margin-left: 25%;
}

.circle li:nth-child(1) {
  top: 0;
  left: 20%;
}

.circle li:nth-child(2) {
  top: 45%;
  left: 65%;
  transform: rotate(90deg);
}

.circle li:nth-child(3) {
  top: 90%;
  left: 20%;
  transform: rotate(180deg);
}

.circle li:nth-child(4) {
  top: 45%;
  left: -25%;
  transform: rotate(270deg);
}

/*HOVER STATES*/
.menu > .one:hover ~ .circle {
  transform: rotate(0deg);
}

.menu > .two:hover ~ .circle {
  transform: rotate(-90deg);
}

.menu > .three:hover ~ .circle {
  transform: rotate(-180deg);
}

.menu > .four:hover ~ .circle {
  transform: rotate(-270deg);
}
</style>
