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
  margin-bottom: 2rem;
  background: var(--ion-color-light);
  border-radius: 16px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
}

.section-title {
  text-align: center;
  font-size: 2.5rem;
}

:slotted(p) {
  padding: 0 1rem 0 1rem;
  text-align: center;
  line-height: 1.4;
  font-size: 1.2rem;
}
</style>