document.addEventListener("DOMContentLoaded", function () {
    const statusMap = {
        "요청": "ST01",
        "발급": "ST02",
        "완료": "ST03"
    };

    const statusCodeMap = {
        "ST01": { name: "요청", class: "btn btn-success statusBtn" },
        "ST02": { name: "발급", class: "btn btn-danger statusBtn" },
        "ST03": { name: "완료", class: "btn status-complete" } // 완료 상태는 'status-complete'로 지정
    };

    function getStatusCode(status) {
        return statusMap[status] || new Error("알 수 없는 상태: " + status);
    }

    function getStatusName(code) {
        return statusCodeMap[code]?.name || "알 수 없음";
    }

    function getButtonClass(code) {
        return statusCodeMap[code]?.class || "btn btn-warning statusBtn";
    }

    const statusButtons = document.querySelectorAll(".statusBtn");

    statusButtons.forEach(button => {
        button.addEventListener("click", function () {
            const currentStatus = this.textContent.trim();

            // "완료" 상태 버튼은 클릭 불가 처리
            if (currentStatus === "완료") {
                console.log("완료 상태 버튼은 클릭할 수 없습니다.");
                return; // 클릭 동작 중단
            }

            console.log("버튼 클릭됨:", currentStatus);

            const stuId = this.closest("tr").dataset.stuId;
            const currentStatusCode = getStatusCode(currentStatus);

            fetch(`${contextPath}studentCard/updateStatus?timestamp=${new Date().getTime()}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: new URLSearchParams({
                    cocoCd: currentStatusCode,
                    stuId: stuId
                }),
                cache: 'no-cache'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error("상태 변경 실패");
                    }

                    return response.json(); // JSON으로 응답 처리
                })
                .then(data => {
                    const newStatus = data.status; // 서버에서 반환된 상태 코드
                    console.log("서버에서 반환된 상태:", newStatus);

                    // 버튼의 상태와 스타일 즉시 업데이트
                    this.textContent = getStatusName(newStatus);
                    this.className = getButtonClass(newStatus);

                    // 새 상태가 "완료"라면 버튼 비활성화 처리
                    if (newStatus === "ST03") {
                        this.classList.add("status-complete");
                        this.setAttribute("disabled", "disabled");
                    }
                })
                .catch(error => {
                    console.error("상태 변경 중 오류 발생:", error);
                    swal("오류", "상태 변경 중 오류 발생", "warning");
                });
        });
    });
});
