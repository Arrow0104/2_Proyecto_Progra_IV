// bolsa-empleo-fe/src/features/auth/pages/LoginPage.jsx
import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { authService } from '../services/authService'
import { getErrorMessage } from '../../../shared/api/apiError'
import Alert from '../../../shared/components/Alert'

export default function LoginPage() {
    const { login } = useAuth()
    const navigate = useNavigate()
    const [form, setForm] = useState({ correo: '', password: '' })
    const [error, setError] = useState('')
    const [cargando, setCargando] = useState(false)

    function handleChange(e) {
        setForm({ ...form, [e.target.name]: e.target.value })
    }

    async function handleSubmit(e) {
        e.preventDefault()
        setError('')
        setCargando(true)
        try {
            const data = await authService.login(form.correo, form.password)
            login(data.token, data.rol, data.usuarioId)
            if (data.rol === 'EMPRESA')  navigate('/empresa')
            else if (data.rol === 'OFERENTE') navigate('/oferente')
            else if (data.rol === 'ADMIN')    navigate('/admin')
            else navigate('/')
        } catch (err) {
            setError(err.status === 401 ? 'Correo o contraseña incorrectos' : getErrorMessage(err))
        } finally {
            setCargando(false)
        }
    }

    return (
        <div style={{ maxWidth: 400, margin: '3rem auto' }}>
            <h2>Iniciar sesión</h2>
            <Alert tipo="error" mensaje={error} />
            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                <label>Correo
                    <input name="correo" type="email" value={form.correo}
                           onChange={handleChange} required style={inputStyle} />
                </label>
                <label>Contraseña
                    <input name="password" type="password" value={form.password}
                           onChange={handleChange} required style={inputStyle} />
                </label>
                <button type="submit" disabled={cargando} style={btnStyle}>
                    {cargando ? 'Ingresando...' : 'Ingresar'}
                </button>
            </form>
            <p style={{ marginTop: '1rem' }}>
                ¿No tenés cuenta? <Link to="/registro/empresa">Empresa</Link> · <Link to="/registro/oferente">Oferente</Link>
            </p>
        </div>
    )
}

const inputStyle = { display: 'block', width: '100%', padding: '0.5rem', marginTop: '0.25rem', boxSizing: 'border-box' }
const btnStyle   = { padding: '0.6rem 1.2rem', background: '#1e3a5f', color: 'white', border: 'none', cursor: 'pointer', borderRadius: '4px' }