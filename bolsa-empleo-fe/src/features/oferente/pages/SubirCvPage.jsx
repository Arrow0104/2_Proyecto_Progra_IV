// bolsa-empleo-fe/src/features/oferente/pages/SubirCvPage.jsx
import { useState } from 'react'
import { oferenteService } from '../services/oferenteService'
import Alert from '../../../shared/components/Alert'

export default function SubirCvPage() {
    const [file, setFile] = useState(null)
    const [mensaje, setMensaje] = useState({ tipo: '', texto: '' })
    const [cargando, setCargando] = useState(false)

    async function handleSubmit(e) {
        e.preventDefault()
        if (!file) return
        setCargando(true)
        try {
            const data = await oferenteService.subirCv(file)
            setMensaje({ tipo: 'success', texto: `CV subido correctamente: ${data.cvPath}` })
        } catch (err) {
            setMensaje({ tipo: 'error', texto: err.mensaje || 'Error al subir CV' })
        } finally {
            setCargando(false)
        }
    }

    return (
        <div style={{ maxWidth: 500, margin: '0 auto' }}>
            <h2>Subir CV</h2>
            <Alert tipo={mensaje.tipo} mensaje={mensaje.texto} />
            <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                <label>Seleccionar archivo PDF
                    <input type="file" accept=".pdf" onChange={e => setFile(e.target.files[0])}
                           required style={{ display: 'block', marginTop: '0.5rem' }} />
                </label>
                <button type="submit" disabled={cargando || !file} style={btnStyle}>
                    {cargando ? 'Subiendo...' : 'Subir CV'}
                </button>
            </form>
        </div>
    )
}

const btnStyle = { padding: '0.6rem 1.2rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }