// bolsa-empleo-fe/src/features/public/pages/PublicSearchPage.jsx
import { useEffect, useState } from 'react'
import { publicService } from '../services/publicService'
import { formatSalario } from '../../../shared/utils/formatters'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function PublicSearchPage() {
    const [puestos, setPuestos] = useState([])
    const [titulo, setTitulo] = useState('')
    const [cargando, setCargando] = useState(true)
    const [error, setError] = useState('')

    function cargar(t) {
        setCargando(true)
        publicService.getPuestosPublicos(t || '')
            .then(setPuestos)
            .catch(() => setError('Error cargando puestos'))
            .finally(() => setCargando(false))
    }

    useEffect(() => { cargar('') }, [])

    function handleBuscar(e) {
        e.preventDefault()
        cargar(titulo)
    }

    if (cargando) return <Loading />

    return (
        <div>
            <h2>Puestos disponibles</h2>
            <Alert tipo="error" mensaje={error} />
            <form onSubmit={handleBuscar} style={{ display: 'flex', gap: '0.5rem', marginBottom: '1.5rem' }}>
                <input
                    value={titulo}
                    onChange={e => setTitulo(e.target.value)}
                    placeholder="Buscar por título..."
                    style={{ flex: 1, padding: '0.5rem', fontSize: '1rem' }}
                />
                <button type="submit" style={btnStyle}>Buscar</button>
                <button type="button" onClick={() => { setTitulo(''); cargar('') }} style={btnSecondary}>Ver todos</button>
            </form>

            {puestos.length === 0
                ? <p>No se encontraron puestos.</p>
                : puestos.map(p => (
                    <div key={p.idPuesto} style={cardStyle}>
                        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                            <h3 style={{ margin: 0 }}>{p.titulo}</h3>
                            <strong style={{ color: '#1e3a5f' }}>{formatSalario(p.salario)}</strong>
                        </div>
                        <p style={{ color: '#555', margin: '0.5rem 0' }}>{p.descripcion}</p>
                        <small style={{ color: '#888' }}>Publicado: {new Date(p.createdAt).toLocaleDateString('es-CR')}</small>
                    </div>
                ))
            }
        </div>
    )
}

const btnStyle     = { padding: '0.5rem 1rem', background: '#1e3a5f', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }
const btnSecondary = { padding: '0.5rem 1rem', background: '#eee', color: '#333', border: 'none', borderRadius: '4px', cursor: 'pointer' }
const cardStyle    = { border: '1px solid #ddd', borderRadius: '8px', padding: '1rem', marginBottom: '1rem', background: 'white' }