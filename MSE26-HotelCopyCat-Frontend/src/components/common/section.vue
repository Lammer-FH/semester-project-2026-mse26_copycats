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
  padding-top: 1rem;
  padding-bottom: 1rem;
  margin-bottom: 4rem;
  background: var(--ion-color-light);
  border-radius: 16px;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.08);
}

.section-title {
  text-align: center;
  font-size: 2.5rem;
}

:slotted(p) {
  padding: 0 2.5rem 0 2.5rem;
  text-align: center;
  line-height: 1.7;
  font-size: 1.2rem;
}
</style>