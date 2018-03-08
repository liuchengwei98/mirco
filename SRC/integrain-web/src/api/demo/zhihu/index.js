import interaction from '../../../common/interaction';

export function getZiHuNews(params) {
  return interaction({
    url: '/api/4/news/latest',
    method: 'get',
    params: params
  });
}

