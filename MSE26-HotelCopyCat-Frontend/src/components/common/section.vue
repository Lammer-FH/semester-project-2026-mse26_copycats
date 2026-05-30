<template>
  <section>
    <div class="section">
      <h1 class="section-title"><b>{{title}}</b></h1>
      <p class="section-text">{{text}}</p>
      <p class="section-text">{{text1}}</p>
    </div>
  </section>
</template>

<script lang="ts">
export default {
  name: 'Section',

  props: {
    title: {
      type: String,
      default: ''
    },
    text: {
      type: String,
      default: ''
    },
    text1: {
      type: String,
      default: ''
    }
  }
}
</script>

<style scoped>
.section{
  padding-top: 0.2rem;
  padding-bottom: 0.2rem;
  background: var(--ion-color-light);
}

.section-title{
  text-align: center;
  font-size: 2.5rem;
}

.section-text{
  text-align: center;
  line-height: 1.4;
  font-size: 1.2rem;
}
</style>