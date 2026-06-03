// bolsa-empleo-fe/src/features/oferente/pages/PerfilPage.jsx
import { useEffect, useState } from 'react'
import { oferenteService } from '../services/oferenteService'
import Alert from '../../../shared/components/Alert'
import Loading from '../../../shared/components/Loading'

export default function PerfilPage() {
    const [form, setForm] = useState(null)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })
    const [cargando, setCargando] = useState(false)

    useEffect(() => {
        oferenteService.getPerfil().then(setForm).catch(() => setMensaje({ tipo: 'error', texto: 'Error cargando perfil' }))
    }, [])

    if (!form) return <Loading />

    function handleChange(e) { setForm({ ...form, [e.target.name]: e.target.value }) }

    async function handleSubmit(e) {
        e.preventDefault()
        setCargando(true)
        try {
            await oferenteService.updatePerfil(form)
            setMensaje({ tipo: 'success', texto: 'Perfil actualizado correctamente' })
        } catch (err) {
            setMensaje({ tipo: 'error', texto: err.mensaje || 'Error al guardar' })
        } finally {
            setCargando(false)
        }
    }

    return (
        <div style={{ maxWidth: 600, margin: '0 auto' }}>
            <h2>Mi perfil</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                {[
                    ['nombre',         'Nombre',        'text'],
                    ['apellido',       'Apellido',       'text'],
                    ['nacionalidad',   'Nacionalidad',   'text'],
                    ['telefono',       'Teléfono',       'text'],
                    ['correoOferente', 'Correo público', 'email'],
                    ['residencia',     'Residencia',     'text'],
                ].map(([name, label, type]) => (
                    <label key={name}>{label}
                        <input name={name} type={type} value={form[name] || ''}
                               onChange={handleChange} style={inputStyle} />
                    </label>
                ))}
                <button type="submit" disabled={cargando} style={btnStyle}>
                    {cargando ? 'Guardando...' : 'Guardar cambios'}
                </button>
            </form>
        </div>
    )
}

const inputStyle = { display: 'block', width: '100%', padding: '0.5rem', marginTop: '0.25rem', boxSizing: 'border-box' }
const btnStyle   = { padding: '0.6rem 1.2rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }