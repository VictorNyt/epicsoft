export default defineNuxtRouteMiddleware(async (to, from) => {
    const principal = usePrincipal();
    const token = useCookie('token');
    const { $auth } = useNuxtApp();
    if (!token.value) {
        console.log("erro1")
        return navigateTo('/login');
    }
    if (!principal.value) {
        console.log("erro4")
        try {
            console.log("erro2")
            principal.value = await $auth.profile();
        } catch (error) {
            console.log("erro3")
            return navigateTo('/login');
        }
    }
})