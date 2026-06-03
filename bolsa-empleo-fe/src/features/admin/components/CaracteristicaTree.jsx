// bolsa-empleo-fe/src/features/admin/components/CaracteristicaTree.jsx
export default function CaracteristicaTree({ caracteristicas, onEliminar }) {
    const raices = caracteristicas.filter(c => c.padreId == null)
    const hijos  = (padreId) => caracteristicas.filter(c => c.padreId === padreId)

    return (
        <ul style={{ listStyle: 'none', paddingLeft: 0 }}>
            {raices.map(r => (
                <li key={r.idCaracteristica} style={{ marginBottom: '0.5rem' }}>
                    <div style={itemStyle}>
                        <strong>{r.nombre}</strong>
                        <button onClick={() => onEliminar(r.idCaracteristica)} style={btnDanger}>✕</button>
                    </div>
                    {hijos(r.idCaracteristica).length > 0 && (
                        <ul style={{ listStyle: 'none', paddingLeft: '1.5rem', marginTop: '0.25rem' }}>
                            {hijos(r.idCaracteristica).map(h => (
                                <li key={h.idCaracteristica} style={{ marginBottom: '0.25rem' }}>
                                    <div style={itemStyle}>
                                        <span>↳ {h.nombre}</span>
                                        <button onClick={() => onEliminar(h.idCaracteristica)} style={btnDanger}>✕</button>
                                    </div>
                                </li>
                            ))}
                        </ul>
                    )}
                </li>
            ))}
        </ul>
    )
}

const itemStyle = { display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '0.4rem 0.75rem', background: '#f5f5f5', borderRadius: '4px' }
const btnDanger = { padding: '0.2rem 0.5rem', background: '#c0392b', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer', fontSize: '0.8rem' }