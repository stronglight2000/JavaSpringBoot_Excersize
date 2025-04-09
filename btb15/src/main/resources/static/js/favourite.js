// Hàm render danh sách phim yêu thích
const renderFavourites = (favourites) => {
    const html = favourites.map(fav => `
        <div class="col-md-6 mb-4">
            <div class="card">
                <img src="${fav.movie.thumbnail}" class="card-img-top" alt="Movie Thumbnail">
                <div class="card-body">
                    <h5 class="card-title">${fav.movie.name}</h5>
                    <p class="card-text">${fav.movie.description}</p>
                    <button class="btn btn-danger" onclick="removeFavourite(${fav.movie.id})">Xóa</button>
                </div>
            </div>
        </div>
    `).join('');
    document.querySelector('.row').innerHTML = html;
    document.querySelector('#favourites-list').innerHTML = html;

    // Thêm nút xóa tất cả nếu có phim yêu thích
    const container = document.querySelector('.container');
    let clearButton = document.getElementById('clear-favourites-btn');
    if (favourites.length > 0) {
        if (!clearButton) {
            clearButton = document.createElement('button');
            clearButton.id = 'clear-favourites-btn';
            clearButton.className = 'btn btn-warning mt-3';
            clearButton.innerText = 'Xóa tất cả';
            clearButton.onclick = clearFavourites;
            container.appendChild(clearButton);
        }
    } else if (clearButton) {
        clearButton.remove();
    }
};

// Hàm lấy danh sách phim yêu thích từ API
const getFavourites = async () => {
    try {
        const response = await axios.get('/api/favourites');
        renderFavourites(response.data);
    } catch (error) {
        console.error("Lỗi khi lấy danh sách phim yêu thích:", error);
    }
};

// Hàm xóa một phim yêu thích
const removeFavourite = async (movieId) => {
    if (!window.confirm("Bạn có chắc chắn muốn xóa phim này khỏi danh sách yêu thích?")) {
        return;
    }
    try {
        await axios.delete(`/api/favourites/${movieId}`);
        getFavourites(); // Cập nhật lại danh sách sau khi xóa
    } catch (error) {
        console.error("Lỗi khi xóa phim yêu thích:", error);
    }
};

// Hàm xóa tất cả phim yêu thích
const clearFavourites = async () => {
    if (!window.confirm("Bạn có chắc chắn muốn xóa tất cả phim yêu thích?")) {
        return;
    }
    try {
        await axios.delete('/api/favourites/clear');
        getFavourites(); // Cập nhật lại danh sách sau khi xóa tất cả
    } catch (error) {
        console.error("Lỗi khi xóa tất cả phim yêu thích:", error);
    }
};

// Gọi hàm lấy danh sách phim yêu thích khi trang load
getFavourites();