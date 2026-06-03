<nav className="navbar">
    <Link to="/" className="navbar-brand">Bolsa de Empleo</Link>
    <ul className="navbar-nav">
        <li><Link to="/buscar" className="nav-link">Buscar puestos</Link></li>
        {!usuario && <>
            <li><Link to="/login" className="nav-link">Iniciar sesión</Link></li>
            <li><Link to="/registro/empresa" className="nav-link">Registrar empresa</Link></li>
            <li><Link to="/registro/oferente" className="nav-link">Registrar oferente</Link></li>
        </>}
        {usuario?.rol === 'EMPRESA' && <>
            <li><Link to="/empresa" className="nav-link">Mi empresa</Link></li>
            <li><Link to="/empresa/puestos" className="nav-link">Puestos</Link></li>
            <li><Link to="/empresa/candidatos" className="nav-link">Candidatos</Link></li>
        </>}
        {usuario?.rol === 'OFERENTE' && <>
            <li><Link to="/oferente" className="nav-link">Dashboard</Link></li>
            <li><Link to="/oferente/perfil" className="nav-link">Perfil</Link></li>
            <li><Link to="/oferente/habilidades" className="nav-link">Habilidades</Link></li>
        </>}
        {usuario?.rol === 'ADMIN' && <>
            <li><Link to="/admin" className="nav-link">Admin</Link></li>
            <li><Link to="/admin/empresas" className="nav-link">Empresas</Link></li>
            <li><Link to="/admin/oferentes" className="nav-link">Oferentes</Link></li>
            <li><Link to="/admin/caracteristicas" className="nav-link">Características</Link></li>
        </>}
        {usuario && <li><button onClick={handleLogout} className="nav-link btn-outline" style={{background:'none', border:'none', cursor:'pointer'}}>Salir</button></li>}
    </ul>
</nav>