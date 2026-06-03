// bolsa-empleo-fe/src/features/auth/utils/jwtUtils.js
export function parseJwt(token) {
    try {
        const base64 = token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')
        return JSON.parse(atob(base64))
    } catch {
        return null
    }
}

export function isTokenExpired(token) {
    const payload = parseJwt(token)
    if (!payload?.exp) return true
    return Date.now() >= payload.exp * 1000
}