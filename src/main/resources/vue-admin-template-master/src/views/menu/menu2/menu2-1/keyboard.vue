<template>
  <div class="chart-container" id="Menu2-1">
    <chart height="100%" width="100%" />
	<router-view />
  </div>
  
</template>

<script>
import Chart from '@/components/Charts/Keyboard'

export default {
  name: 'KeyboardChart',
  components: { Chart }
}
</script>

<style scoped>
.chart-container{
  position: relative;
  width: 100%;
  height: calc(100vh - 84px);
}
</style>

