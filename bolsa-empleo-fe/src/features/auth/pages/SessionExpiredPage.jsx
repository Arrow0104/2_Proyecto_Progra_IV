// bolsa-empleo-fe/src/features/auth/pages/SessionExpiredPage.jsx
import { Link } from 'react-router-dom'

export default function SessionExpiredPage() {
    return (
        <div style={{ textAlign: 'center', marginTop: '4rem' }}>
            <h2>⏳ Sesión expirada</h2>
            <p>Tu sesión venció. Por favor iniciá sesión nuevamente.</p>
            <Link to="/login">Iniciar sesión</Link>
        </div>
    )
}