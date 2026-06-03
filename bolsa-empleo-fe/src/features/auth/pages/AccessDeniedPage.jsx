// bolsa-empleo-fe/src/features/auth/pages/AccessDeniedPage.jsx
import { Link } from 'react-router-dom'

export default function AccessDeniedPage() {
    return (
        <div style={{ textAlign: 'center', marginTop: '4rem' }}>
            <h2>⛔ Acceso denegado</h2>
            <p>No tenés permiso para ver esta página.</p>
            <Link to="/">Volver al inicio</Link>
        </div>
    )
}