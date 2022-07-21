<template>
  <div>
    <BHeader/>
    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <BAside/>
      <!--      内容-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import BHeader from "@/components/BHeader";
import BAside from "@/components/BAside";

export default {
  name: "Layout",
  components: {
    BAside,
    BHeader
  }
}
</script>

<style scoped>

</style>