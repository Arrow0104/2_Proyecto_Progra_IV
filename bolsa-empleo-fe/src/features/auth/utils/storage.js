// bolsa-empleo-fe/src/features/auth/utils/storage.js
const TOKEN_KEY = 'token'
const ROL_KEY   = 'rol'
const UID_KEY   = 'usuarioId'

export const storage = {
    saveSession: (token, rol, usuarioId) => {
        localStorage.setItem(TOKEN_KEY, token)
        localStorage.setItem(ROL_KEY, rol)
        localStorage.setItem(UID_KEY, String(usuarioId))
    },
    getToken:     () => localStorage.getItem(TOKEN_KEY),
    getRol:       () => localStorage.getItem(ROL_KEY),
    getUsuarioId: () => Number(localStorage.getItem(UID_KEY)),
    clear:        () => {
        localStorage.removeItem(TOKEN_KEY)
        localStorage.removeItem(ROL_KEY)
        localStorage.removeItem(UID_KEY)
    },
    isLoggedIn:   () => !!localStorage.getItem(TOKEN_KEY)
}