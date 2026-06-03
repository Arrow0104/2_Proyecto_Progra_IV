// bolsa-empleo-fe/src/features/registro/pages/RegistroEmpresaPage.jsx
import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import { registroService } from '../services/registroService'
import Alert from '../../../shared/components/Alert'

const VACIO = { correo: '', identificacion: '', password: '', nombre: '', telefono: '', localizacion: '', correoEmpresa: '', descripcion: '' }

export default function RegistroEmpresaPage() {
    const [form, setForm] = useState(VACIO)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })
    const [cargando, setCargando] = useState(false)
    const navigate = useNavigate()

    function handleChange(e) { setForm({ ...form, [e.target.name]: e.target.value }) }

    async function handleSubmit(e) {
        e.preventDefault()
        setCargando(true)
        try {
            await registroService.registrarEmpresa(form)
            setMensaje({ tipo: 'success', texto: 'Registro exitoso. Podés iniciar sesión.' })
            setTimeout(() => navigate('/login'), 2000)
        } catch (err) {
            setMensaje({ tipo: 'error', texto: err.mensaje || 'Error al registrar' })
        } finally {
            setCargando(false)
        }
    }

    return (
        <div style={{ maxWidth: 550, margin: '2rem auto' }}>
            <h2>Registrar empresa</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                <fieldset style={fieldsetStyle}>
                    <legend>Cuenta de acceso</legend>
                    {[['correo','Correo','email'],['identificacion','Cédula jurídica','text'],['password','Contraseña','password']].map(([n,l,t]) => (
                        <label key={n} style={{ display:'block', marginBottom:'0.5rem' }}>{l}
                            <input name={n} type={t} value={form[n]} onChange={handleChange} required style={inputStyle} />
                        </label>
                    ))}
                </fieldset>
                <fieldset style={fieldsetStyle}>
                    <legend>Datos de la empresa</legend>
                    {[['nombre','Nombre de empresa','text'],['telefono','Teléfono','text'],['localizacion','Localización','text'],['correoEmpresa','Correo público de empresa','email']].map(([n,l,t]) => (
                        <label key={n} style={{ display:'block', marginBottom:'0.5rem' }}>{l}
                            <input name={n} type={t} value={form[n]} onChange={handleChange} style={inputStyle} />
                        </label>
                    ))}
                    <label style={{ display:'block' }}>Descripción
                        <textarea name="descripcion" value={form.descripcion} onChange={handleChange} rows={3} style={inputStyle} />
                    </label>
                </fieldset>
                <button type="submit" disabled={cargando} style={btnStyle}>
                    {cargando ? 'Registrando...' : 'Registrarse'}
                </button>
            </form>
            <p>¿Ya tenés cuenta? <Link to="/login">Iniciar sesión</Link></p>
        </div>
    )
}

const inputStyle   = { display:'block', width:'100%', padding:'0.5rem', marginTop:'0.25rem', boxSizing:'border-box' }
const btnStyle     = { padding:'0.6rem 1.2rem', background:'#1e3a5f', color:'white', border:'none', borderRadius:'4px', cursor:'pointer' }
const fieldsetStyle = { border:'1px solid #ddd', borderRadius:'6px', padding:'1rem', marginBottom:'0.5rem' }