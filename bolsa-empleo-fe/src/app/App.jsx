// bolsa-empleo-fe/src/app/App.jsx
import { AuthProvider } from '../features/auth/context/AuthContext'
import AppRouter from './router/AppRouter'

export default function App() {
    return (
        <AuthProvider>
            <AppRouter />
        </AuthProvider>
    )
}