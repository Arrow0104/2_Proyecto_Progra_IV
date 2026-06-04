import { useEffect, useState } from 'react'
import { publicService } from '../services/publicService'
import { api } from '../../../shared/api/apiClient'
import { formatSalario } from '../../../shared/utils/formatters'
import Loading from '../../../shared/components/Loading'
import Alert from '../../../shared/components/Alert'

export default function PublicSearchPage() {
    const [puestos, setPuestos] = useState([])
    const [titulo, setTitulo] = useState('')
    const [caracteristicas, setCaracteristicas] = useState([])
    const [seleccionadas, setSeleccionadas] = useState([])
    const [cargando, setCargando] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        Promise.all([
            publicService.getPuestosPublicos(''),
            api.get('/api/public/caracteristicas').catch(() => [])
        ]).then(([p]) => setPuestos(p))
            .catch(() => setError('Error cargando puestos'))
            .finally(() => setCargando(false))

        // Cargar características sin login
        fetch('/api/public/puestos')
            .then(() => api.get('/caracteristicas').catch(() => []))
            .catch(() => [])

        publicService.getPuestosPublicos('').then(setPuestos).catch(() => {})

        // Intentar cargar características (pueden estar disponibles o no sin login)
        fetch('/api/caracteristicas')
            .then(r => r.ok ? r.json() : [])
            .then(setCaracteristicas)
            .catch(() => setCaracteristicas([]))
    }, [])

    function toggleCar(id) {
        setSeleccionadas(prev =>
            prev.includes(id) ? prev.filter(x => x !== id) : [...prev, id]
        )
    }

    async function buscar(e) {
        e.preventDefault()
        setCargando(true)
        try {
            const data = await publicService.getPuestosPublicos(titulo)
            if (seleccionadas.length === 0) {
                setPuestos(data)
            } else {
                setPuestos(data.filter(p =>
                    seleccionadas.every(id =>
                        p.caracteristicas?.some(c => c.idCaracteristica === id)
                    )
                ))
            }
        } catch {
            setError('Error en la búsqueda')
        } finally {
            setCargando(false)
        }
    }

    const raices = caracteristicas.filter(c => c.padreId == null)
    const hijos = (padreId) => caracteristicas.filter(c => c.padreId === padreId)

    if (cargando) return <Loading />

    return (
        <div style={{ display: 'grid', gridTemplateColumns: '260px 1fr', gap: '2rem' }}>
            {/* Panel izquierdo - filtros */}
            <aside>
                <form onSubmit={buscar}>
                    <h3 style={{ marginBottom: '1rem' }}>Buscar Puestos</h3>
                    <input
                        value={titulo}
                        onChange={e => setTitulo(e.target.value)}
                        placeholder="Buscar por título..."
                        className="form-control"
                        style={{ marginBottom: '1rem' }}
                    />
                    {caracteristicas.length > 0 && (
                        <ul className="tree-list">
                            {raices.map(r => (
                                <li key={r.idCaracteristica} className="tree-parent">
                                    <label>
                                        <input type="checkbox"
                                               checked={seleccionadas.includes(r.idCaracteristica)}
                                               onChange={() => toggleCar(r.idCaracteristica)} />
                                        {' '}{r.nombre}
                                    </label>
                                    {hijos(r.idCaracteristica).length > 0 && (
                                        <ul className="tree-children">
                                            {hijos(r.idCaracteristica).map(h => (
                                                <li key={h.idCaracteristica}>
                                                    <label>
                                                        <input type="checkbox"
                                                               checked={seleccionadas.includes(h.idCaracteristica)}
                                                               onChange={() => toggleCar(h.idCaracteristica)} />
                                                        {' '}{h.nombre}
                                                    </label>
                                                </li>
                                            ))}
                                        </ul>
                                    )}
                                </li>
                            ))}
                        </ul>
                    )}
                    <button type="submit" className="btn btn-primary" style={{ marginTop: '1rem', width: '100%' }}>
                        Buscar
                    </button>
                </form>
            </aside>

            {/* Panel derecho - resultados */}
            <div>
                <h2>Resultados</h2>
                <Alert tipo="error" mensaje={error} />
                {puestos.length === 0
                    ? <p>No se encontraron puestos.</p>
                    : puestos.map(p => (
                        <div key={p.idPuesto} className="card" style={{ marginBottom: '1rem' }}>
                            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                                <div className="card-title">{p.titulo}</div>
                                <strong style={{ color: 'var(--accent)' }}>{formatSalario(p.salario)}</strong>
                            </div>
                            <p style={{ color: 'var(--text-sec)', margin: '0.5rem 0' }}>{p.descripcion}</p>
                            <small style={{ color: 'var(--text-muted)' }}>
                                Publicado: {new Date(p.createdAt).toLocaleDateString('es-CR')}
                            </small>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}