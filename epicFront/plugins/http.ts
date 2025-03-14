import AuthService from "~/services/authService"
import UserService from "~/services/userService"

export default defineNuxtPlugin(() => {
    const error = ref<{ message: string; status: number; timestamp: number }>();

    const http = $fetch.create({
        baseURL: "http://localhost:8080",

        onRequest: ({ options, request }) => {
            const token = useCookie('token').value;
            if (token && request !== '/auth/login') {
                console.log("deu certo de auth");
                options.headers.append('Authorization', `Bearer ${token}`)
            }
            else{
                console.log("erro de auth");
            }
        },

        onResponseError: ({ response }: any) => {
            // const { status, message, timestamp } = response._data;
            // error.value = { status, message, timestamp };
            throw { message: "Erro" }
        }
    })
    const auth = new AuthService(http);
    const user = new UserService(http);
    return { provide: { http, user, auth, error } }
}) 