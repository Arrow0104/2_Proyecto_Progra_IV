// bolsa-empleo-fe/src/features/oferente/components/HabilidadForm.jsx
import { useState } from 'react'

export default function HabilidadForm({ caracteristicas, onAgregar }) {
    const [idCaracteristica, setIdCaracteristica] = useState('')
    const [nivel, setNivel] = useState(3)

    async function handleSubmit(e) {
        e.preventDefault()
        if (!idCaracteristica) return
        await onAgregar({ idCaracteristica: Number(idCaracteristica), nivel: Number(nivel) })
        setIdCaracteristica('')
        setNivel(3)
    }

    return (
        <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '0.75rem', alignItems: 'flex-end', flexWrap: 'wrap', marginBottom: '1rem' }}>
            <label style={{ flex: 2 }}>Característica
                <select value={idCaracteristica} onChange={e => setIdCaracteristica(e.target.value)}
                        required style={inputStyle}>
                    <option value="">-- Seleccionar --</option>
                    {caracteristicas.map(c => (
                        <option key={c.idCaracteristica} value={c.idCaracteristica}>{c.nombre}</option>
                    ))}
                </select>
            </label>
            <label style={{ flex: 1 }}>Nivel (1–5)
                <input type="number" min={1} max={5} value={nivel}
                       onChange={e => setNivel(e.target.value)} style={inputStyle} />
            </label>
            <button type="submit" style={btnStyle}>Agregar</button>
        </form>
    )
}

const inputStyle = { display: 'block', width: '100%', padding: '0.5rem', marginTop: '0.25rem', boxSizing: 'border-box' }
const btnStyle   = { padding: '0.55rem 1rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }