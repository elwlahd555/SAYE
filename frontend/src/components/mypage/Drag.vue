<template>
  <li class="item">
    <span class="item__drag">{{ title }}</span>
  </li>
</template>

<script>
import gsap from "gsap";
import Draggable from "gsap/Draggable";
gsap.registerPlugin(Draggable);

export default {
  props: ["title"],
  mounted() {
    this.createItem(".item__drag");
  },
  methods: {
    dragItem(element) {
      if (element.hitTest(".dropzone", "50%")) {
        this.hitItem(true);
      } else {
        this.hitItem(false);
      }
    },
    cloneItem(element) {
      let clone = element.target.cloneNode(true);
      let parent = element.target.parentNode;

      gsap.set(clone, { clearProps: "all" });

      parent.appendChild(clone);

      this.createItem(clone);
    },
    createItem(element) {
      var self = this;

      Draggable.create(element, {
        type: "x,y",
        onPress: function() {
          self.cloneItem(this);
        },
        onDrag: function() {
          self.dragItem(this);
        },
        onRelease: function() {
          self.releaseItem(this);
        }
      });
    },
    releaseItem(element) {
      if (element.hitTest(".dropzone", "50%")) {
        this.removeItem(element);
      } else {
        gsap.to(element.target, {
          duration: 0.3,
          x: 0,
          y: 0,
          ease: "elastic",
          onComplete: () => this.removeItem(element)
        });
      }
    },
    removeItem(element) {
      element.kill();
      element.target.remove();
      this.$parent.$emit("hitDropZone", false);
    },
    hitItem(isOver) {
      this.$parent.$emit("hitDropZone", isOver);
    }
  }
};
</script>

<style lang="scss" scoped>
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
</style>
