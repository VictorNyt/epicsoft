<script setup lang="ts">
definePageMeta({ middleware: 'auth' })
const token = useCookie("token");
</script>

<template>
    {{ token }}
</template>


<style scoped>
/*style scoped muda apenas essa pagina*/
</style>