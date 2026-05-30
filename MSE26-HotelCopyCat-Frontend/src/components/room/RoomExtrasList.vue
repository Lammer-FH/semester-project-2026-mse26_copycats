<template>
  <div class="extras-list">
    <div v-for="extra in extras" :key="extra.id" class="extra-item">
      <img :src="getExtraIconUrl(extra.iconPath)" class="extra-icon" :alt="extra.name" />
      <span class="extra-name">{{ extra.name }}</span>
    </div>
  </div>
</template>

<script lang="ts">
import type { RoomExtra } from '@/models/Room'
import { getExtraIconUrl } from '@/models/Room'

export default {
  name: 'RoomExtrasList',
  props: {
    extras: {
      type: Array as () => RoomExtra[],
      required: true
    }
  },

  methods: {
    getExtraIconUrl
  }
}
</script>

<style scoped>
.extras-list {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.extra-item {
  display: flex;
  align-items: center;
  gap: 6px;
}

.extra-icon {
  width: 22px;
  height: 22px;
  opacity: 0.85;
}

@media (prefers-color-scheme: dark) {
  .extra-icon {
    filter: invert(1);
  }
}

.extra-name {
  font-size: 0.875rem;
  color: var(--ion-text-color, #555);
}
</style>
