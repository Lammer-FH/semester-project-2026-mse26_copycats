<template>
  <section>
    <div class="section">
      <h1 class="section-title"><b>{{ title }}</b></h1>
      <slot />
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
    }
  }
}
</script>

<style scoped>
.section {
  padding-top: 0.2rem;
  padding-bottom: 0.2rem;
  background: var(--ion-color-light);
}

.section-title {
  text-align: center;
  font-size: 2.5rem;
}

:slotted(p) {
  text-align: center;
  line-height: 1.4;
  font-size: 1.2rem;
}
</style>